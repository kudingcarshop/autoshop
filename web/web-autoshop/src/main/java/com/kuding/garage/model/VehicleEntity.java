package com.kuding.garage.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kuding.customer.model.VehicleMaintainInfo;
import com.kuding.system.model.UserEntity;

@Entity
@Table(name="vehicle_info")
public class VehicleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="last_garage_date")
	private Date lastGarageDate;
	
	@Column(name="violation_count")
	private Integer violationCount;
	
	@Column(name="total_consumption")
	private float totalConsumption;
	
	@Column(name="total_debt")
	private float totalDebt;
	
	@Column(name="last_mileage")
	private Integer lastMileage;
	
	@Column(name = "insurance_date")
	private Date insuranceDate;
	
	@Column(name = "last_maintain_date")
	private Date lastMaintainDate;
	
	@Column(name = "last_maintain_mileage")
	private Integer lastMaintainMileage;
	
	@Column(name = "tire_service_year")
	private Integer tireServiceYear;
	
	@Column(name = "battery_service_year")
	private Integer batteryServiceYear;
	
	@Column(name = "last_beauty_date")
	private Date lastBeautyDate;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(name="plate_number")
	private String plateNumber;
	
	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "use_character")
	private String useCharacter;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "vin")
	private String vin;
	
	@Column(name="engine_no")
	private String engineNumber;

	@Column(name="register_date")
	private Date registerDate;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="file_no")
	private String fileNo;
	
	@Column(name="approved_passengers_apacity")
	private Integer passangerCapacity;
	
	@Column(name="gross_mass")
	private Integer grossMass;
	
	@Column(name="unloaden_mass")
	private Integer unloadenMass;
	
	@Column(name="approved_load")
	private Integer approvedLoad;
	
	@Column(name="overall_dimension")
	private String overallDimension;
	
	@Column(name="traction_mass")
	private Integer tractionMass;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="inspection_record")
	private String inspectionRecord;
	
	@OneToMany(mappedBy="vehicle",fetch=FetchType.LAZY)
	private Set<VehicleMaintainInfo> maintains;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLastGarageDate() {
		return lastGarageDate;
	}

	public void setLastGarageDate(Date lastGarageDate) {
		this.lastGarageDate = lastGarageDate;
	}

	public Integer getViolationCount() {
		return violationCount;
	}

	public void setViolationCount(Integer violationCount) {
		this.violationCount = violationCount;
	}

	public float getTotalConsumption() {
		return totalConsumption;
	}

	public void setTotalConsumption(float totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public float getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(float totalDebt) {
		this.totalDebt = totalDebt;
	}

	public Integer getLastMileage() {
		return lastMileage;
	}

	public void setLastMileage(Integer lastMileage) {
		this.lastMileage = lastMileage;
	}

	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}

	public Integer getLastMaintainMileage() {
		return lastMaintainMileage;
	}

	public void setLastMaintainMileage(Integer lastMaintainMileage) {
		this.lastMaintainMileage = lastMaintainMileage;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	public Integer getTireServiceYear() {
		return tireServiceYear;
	}

	public void setTireServiceYear(Integer tireServiceYear) {
		this.tireServiceYear = tireServiceYear;
	}

	public Integer getBatteryServiceYear() {
		return batteryServiceYear;
	}

	public void setBatteryServiceYear(Integer batteryServiceYear) {
		this.batteryServiceYear = batteryServiceYear;
	}

	public Date getLastBeautyDate() {
		return lastBeautyDate;
	}

	public void setLastBeautyDate(Date lastBeautyDate) {
		this.lastBeautyDate = lastBeautyDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUseCharacter() {
		return useCharacter;
	}

	public void setUseCharacter(String useCharacter) {
		this.useCharacter = useCharacter;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public Integer getPassangerCapacity() {
		return passangerCapacity;
	}

	public void setPassangerCapacity(Integer passangerCapacity) {
		this.passangerCapacity = passangerCapacity;
	}

	public Integer getGrossMass() {
		return grossMass;
	}

	public void setGrossMass(Integer grossMass) {
		this.grossMass = grossMass;
	}

	public Integer getUnloadenMass() {
		return unloadenMass;
	}

	public void setUnloadenMass(Integer unloadenMass) {
		this.unloadenMass = unloadenMass;
	}

	public Integer getApprovedLoad() {
		return approvedLoad;
	}

	public void setApprovedLoad(Integer approvedLoad) {
		this.approvedLoad = approvedLoad;
	}

	public String getOverallDimension() {
		return overallDimension;
	}

	public void setOverallDimension(String overallDimension) {
		this.overallDimension = overallDimension;
	}

	public Integer getTractionMass() {
		return tractionMass;
	}

	public void setTractionMass(Integer tractionMass) {
		this.tractionMass = tractionMass;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getInspectionRecord() {
		return inspectionRecord;
	}

	public void setInspectionRecord(String inspectionRecord) {
		this.inspectionRecord = inspectionRecord;
	}
	

}
