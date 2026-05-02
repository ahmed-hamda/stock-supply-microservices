package com.stock.supply_service.service;

import com.stock.supply_service.client.ProductClient;
import com.stock.supply_service.client.SupplierClient;
import com.stock.supply_service.dto.SupplyItemRequest;
import com.stock.supply_service.dto.SupplyOrderRequest;
import com.stock.supply_service.dto.SupplyOrderResponse;
import com.stock.supply_service.entity.SupplyItem;
import com.stock.supply_service.entity.SupplyOrder;
import com.stock.supply_service.entity.SupplyStatus;
import com.stock.supply_service.exception.*;
import com.stock.supply_service.external.ProductResponse;
import com.stock.supply_service.external.SupplierProductResponse;
import com.stock.supply_service.mapper.SupplyMapper;
import com.stock.supply_service.repository.SupplyOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final ProductClient productClient;
    private final SupplierClient supplierClient;

    public SupplyOrderService(
            SupplyOrderRepository supplyOrderRepository,
            ProductClient productClient,
            SupplierClient supplierClient
    ) {
        this.supplyOrderRepository = supplyOrderRepository;
        this.productClient = productClient;
        this.supplierClient = supplierClient;
    }

    public SupplyOrderResponse createOrder(SupplyOrderRequest request) {
        SupplyOrder order = new SupplyOrder();
        order.setSupplierId(request.getSupplierId());
        order.setDate(LocalDate.now());
        order.setStatus(SupplyStatus.CREATED);

        SupplyOrder savedOrder = supplyOrderRepository.save(order);
        return SupplyMapper.toOrderResponse(savedOrder);
    }

    public List<SupplyOrderResponse> getAllOrders() {
        return supplyOrderRepository.findAll()
                .stream()
                .map(SupplyMapper::toOrderResponse)
                .toList();
    }

    public SupplyOrderResponse getOrderById(Long id) {
        SupplyOrder order = findOrderById(id);
        return SupplyMapper.toOrderResponse(order);
    }

    public SupplyOrderResponse addItem(Long orderId, SupplyItemRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new InvalidQuantityException();
        }

        SupplyOrder order = findOrderById(orderId);

        if (order.getStatus() != SupplyStatus.CREATED) {
            throw new RuntimeException("Impossible d'ajouter des items à une commande déjà traitée");
        }

        ProductResponse product = productClient.getProductById(request.getProductId());
        SupplierProductResponse supplierProduct =
                supplierClient.getSupplierProductById(request.getSupplierProductId());

        if (!product.getReference().equals(supplierProduct.getReference())) {
            throw new RuntimeException("La référence du produit commerçant ne correspond pas au produit fournisseur");
        }

        if (!request.getReference().equals(product.getReference())) {
            throw new RuntimeException("La référence envoyée ne correspond pas au produit commerçant");
        }

        if (!order.getSupplierId().equals(supplierProduct.getSupplierId())) {
            throw new RuntimeException("Le produit fournisseur n'appartient pas au fournisseur de cette commande");
        }

        SupplyItem item = new SupplyItem();
        item.setProductId(request.getProductId());
        item.setSupplierProductId(request.getSupplierProductId());
        item.setReference(request.getReference());
        item.setQuantity(request.getQuantity());
        item.setSupplyOrder(order);

        order.getItems().add(item);

        SupplyOrder updatedOrder = supplyOrderRepository.save(order);
        return SupplyMapper.toOrderResponse(updatedOrder);
    }

    public SupplyOrderResponse validateOrder(Long orderId) {
        SupplyOrder order = findOrderById(orderId);

        if (order.getStatus() == SupplyStatus.VALIDATED) {
            throw new SupplyOrderAlreadyValidatedException(orderId);
        }

        if (order.getStatus() == SupplyStatus.RECEIVED) {
            throw new SupplyOrderAlreadyReceivedException(orderId);
        }

        if (order.getItems().isEmpty()) {
            throw new RuntimeException("La commande ne contient aucun item");
        }

        for (SupplyItem item : order.getItems()) {
            ProductResponse product = productClient.getProductById(item.getProductId());
            SupplierProductResponse supplierProduct =
                    supplierClient.getSupplierProductById(item.getSupplierProductId());

            if (!product.getReference().equals(supplierProduct.getReference())) {
                throw new RuntimeException("Référence incompatible pour l'item : " + item.getId());
            }

            if (!order.getSupplierId().equals(supplierProduct.getSupplierId())) {
                throw new RuntimeException("Produit fournisseur non associé au fournisseur de la commande");
            }

            if (supplierProduct.getAvailableQuantity() < item.getQuantity()) {
                throw new InsufficientSupplierStockException(item.getSupplierProductId());
            }
        }

        order.setStatus(SupplyStatus.VALIDATED);

        SupplyOrder savedOrder = supplyOrderRepository.save(order);
        return SupplyMapper.toOrderResponse(savedOrder);
    }

    public SupplyOrderResponse receiveOrder(Long orderId) {
        SupplyOrder order = findOrderById(orderId);

        if (order.getStatus() == SupplyStatus.RECEIVED) {
            throw new SupplyOrderAlreadyReceivedException(orderId);
        }

        if (order.getStatus() != SupplyStatus.VALIDATED) {
            throw new RuntimeException("La commande doit être validée avant la réception");
        }

        for (SupplyItem item : order.getItems()) {
            productClient.increaseStock(item.getProductId(), item.getQuantity());
            supplierClient.decreaseSupplierStock(item.getSupplierProductId(), item.getQuantity());
        }

        order.setStatus(SupplyStatus.RECEIVED);

        SupplyOrder savedOrder = supplyOrderRepository.save(order);
        return SupplyMapper.toOrderResponse(savedOrder);
    }

    public void cancelOrder(Long orderId) {
        SupplyOrder order = findOrderById(orderId);

        if (order.getStatus() == SupplyStatus.RECEIVED) {
            throw new RuntimeException("Impossible d'annuler une commande déjà reçue");
        }

        order.setStatus(SupplyStatus.CANCELLED);
        supplyOrderRepository.save(order);
    }

    private SupplyOrder findOrderById(Long id) {
        return supplyOrderRepository.findById(id)
                .orElseThrow(() -> new SupplyOrderNotFoundException(id));
    }
}