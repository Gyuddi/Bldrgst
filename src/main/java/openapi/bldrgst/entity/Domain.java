package openapi.bldrgst.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import openapi.bldrgst.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Domain {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String houseName;
    private String buildUse;
    private String buildingStructure;
    private String houseHold;
    private String useAprDay;
    private String grndFloor;
    private String ugrndFloor;
    private String elevator;



}
