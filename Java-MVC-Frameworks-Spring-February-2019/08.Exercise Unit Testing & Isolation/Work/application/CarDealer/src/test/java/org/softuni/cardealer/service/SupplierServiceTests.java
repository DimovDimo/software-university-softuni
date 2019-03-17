package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {

    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    @Test
    public void supplierService_saveSupplierWithCorrectValues_ReturnsCorrected(){
        //Arrange
        SupplierServiceModel toBeSaved = new SupplierServiceModel();
        toBeSaved.setName("Pesho");
        toBeSaved.setImporter(true);

        //Act
        SupplierServiceModel actual = supplierService.saveSupplier(toBeSaved);
        SupplierServiceModel expected = this.modelMapper.map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        //Assert
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_saveSupplierWithNUllValues_ThrowsException(){
        //Arrange
        SupplierServiceModel toBeSaved = new SupplierServiceModel();

        //Act
        toBeSaved.setName(null);

        //Assert
        supplierService.saveSupplier(toBeSaved);
    }

    @Test
    public void supplierService_editSupplierWithCorrectValues_ReturnsCorrect(){
        //Arrange
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplier = this.supplierRepository.saveAndFlush(supplier);
        SupplierServiceModel toBeEdited = new SupplierServiceModel();

        //Act
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName("Gosho");
        toBeEdited.setImporter(false);

        SupplierServiceModel actual = supplierService.editSupplier(toBeEdited);
        SupplierServiceModel expected = this.modelMapper.map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        //Assert
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_editSupplierWithNullValues_ThrowsException(){
        //Arrange
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        //Act
        supplier = this.supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel toBeEdited = new SupplierServiceModel();
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName(null);

        //Assert
        supplierService.editSupplier(toBeEdited);
    }

    @Test
    public void supplierService_deleteSupplierWithValidId_ReturnsCorrect(){
        //Arrange
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);

        //Act
        supplierService.deleteSupplier(supplier.getId());

        long expected = 0;
        long actual = this.supplierRepository.findAll().size();

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithValidId_ThrowsException(){
        //Arrange
        Supplier supplier = new Supplier();

        //Act
        supplier.setName("Pesho");
        supplier.setImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);

        //Assert
        supplierService.deleteSupplier("InvalidId");
    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithNull_ThrowsException(){
        //Assert
        this.supplierService.deleteSupplier(null);
    }

    @Test
    public void supplierService_findSupplierByIdWithValidId_ReturnsCorrect(){
        //Arrange
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);

        //Act
        SupplierServiceModel expected = this.supplierRepository
                .findById(supplier.getId())
                .map(s -> this.modelMapper.map(s, SupplierServiceModel.class))
                .orElse(null);

        SupplierServiceModel actual = this.supplierService.findSupplierById(supplier.getId());

        //Assert
        assert expected != null;
        Assert.assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void supplierService_findSupplierByIdWithValidId_ThrowsException(){
        //Arrange
        Supplier supplier = new Supplier();

        //Act
        supplier.setName("Pesho");
        supplier.setImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);

        //Assert
        supplierService.findSupplierById("InvalidId");
    }

    @Test(expected = Exception.class)
    public void supplierService_findSupplierByIdWithNullValue_ThrowsException(){
        //Assert
        this.supplierService.findSupplierById(null);
    }
}
