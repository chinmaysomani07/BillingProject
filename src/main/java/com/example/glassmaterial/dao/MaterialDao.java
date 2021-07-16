package com.example.glassmaterial.dao;

import com.example.glassmaterial.model.Material;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialDao extends CrudRepository<Material, Integer> {

    @Query("select m from Material m")
    public List<Material> getAllMaterials();

    @Query("select m.price from Material m where m.type=:material and m.sizeinmm=:sizeinmm")
    public Double getPriceOfMaterial(@Param("material") String material, @Param("sizeinmm") int sizeinmm);

    @Query("select w.price from Work w where w.type=:work")
    public Double getPriceOfWork(@Param("work") String work);
    
    @Query("select m.sizeinmm from Material m where m.type=:material")
    public List<Double> getSizeInmm(@Param("material") String material);

    @Query("select distinct m.type from Material m")
    public List<String> getAllMaterialName();

}
