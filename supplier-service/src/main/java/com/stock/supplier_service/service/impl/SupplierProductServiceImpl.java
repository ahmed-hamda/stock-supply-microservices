package com.stock.supplier_service.service.impl;

import com.stock.supplier_service.exception.DuplicateResourceException;
import com.stock.supplier_service.exception.InvalidQuantityException;
import com.stock.supplier_service.exception.ResourceNotFoundException;
import com.stock.supplier_service.mapper.SupplierProductMapper;
import com.stock.supplier_service.repository.SupplierProductRepository;
import com.stock.supplier_service.repository.SupplierRepository;
import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.dto.response.SupplierProductResponse;
import com.stock.supplier_service.entity.SupplierProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.stock.supplier_service.service.SupplierProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository repository;
    private final SupplierRepository supplierRepository;

    public SupplierProductResponse create(SupplierProductRequest request) {

        // RULE: supplier must exist
        if (!supplierRepository.existsById(request.getSupplierId())) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        // RULE: stock >= 0
        if (request.getAvailableQuantity() < 0) {
            throw new InvalidQuantityException("Quantity must be positive");
        }

        SupplierProduct entity = SupplierProductMapper.toEntity(request);

        return SupplierProductMapper.toResponse(repository.save(entity));
    }

    @Override
    public SupplierProductResponse getByReference(String reference) {

        SupplierProduct entity = repository.findByReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return SupplierProductMapper.toResponse(entity);
    }


    @Override
    public List<SupplierProductResponse> getBySupplier(Long supplierId) {

        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        return repository.findBySupplierId(supplierId)
                .stream()
                .map(SupplierProductMapper::toResponse)
                .toList();
    }

    @Override
    public SupplierProductResponse getCheapestByReference(String reference) {
        SupplierProduct product = repository.findFirstByReferenceOrderBySupplierPriceAsc(reference)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aucun produit fournisseur trouvé pour la référence : " + reference
                ));

        return SupplierProductMapper.toResponse(product);
    }

    public SupplierProductResponse getById(Long id) {
        SupplierProduct product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit fournisseur introuvable avec id : " + id));

        return SupplierProductMapper.toResponse(product);
    }

    public SupplierProductResponse getBySupplierAndReference(Long supplierId, String reference) {
        SupplierProduct product = repository.findBySupplierIdAndReference(supplierId, reference)
                .orElseThrow(() -> new RuntimeException(
                        "Produit fournisseur introuvable pour supplierId = " + supplierId + " et reference = " + reference
                ));

        return SupplierProductMapper.toResponse(product);
    }

    public SupplierProductResponse decreaseStock(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("La quantité doit être strictement positive");
        }

        SupplierProduct product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit fournisseur introuvable avec id : " + id));

        if (product.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Stock fournisseur insuffisant");
        }

        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);

        SupplierProduct saved = repository.save(product);

        return SupplierProductMapper.toResponse(saved);
    }

}