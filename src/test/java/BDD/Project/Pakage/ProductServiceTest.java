package BDD.Project.Pakage;

import BDD.Project.Pakage.model.Product;
import BDD.Project.Pakage.repo.ProductRepository;
import BDD.Project.Pakage.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;  // Correct static imports for JUnit
import static org.mockito.Mockito.*;


public class ProductServiceTest {  // Ensure class is public

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Use try-with-resources for AutoCloseable
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1200.0);

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getName());
        assertEquals(1200.0, createdProduct.getPrice());
        verify(productRepository, times(1)).save(product);  // Correct usage
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Laptop");
        Product product2 = new Product();
        product2.setName("Smartphone");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);
        assertTrue(foundProduct.isPresent());
        assertEquals("Laptop", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);
        assertNotNull(updatedProduct);
        assertEquals("Laptop", updatedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        doNothing().when(productRepository).deleteById(productId);
        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}