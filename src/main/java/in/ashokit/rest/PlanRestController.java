package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.PlanService;

@RestController
public class PlanRestController {
	@Autowired
	private PlanService planService;
	

	@GetMapping("/categories")
  public ResponseEntity<Map<Integer, String>> planCategories(){
	  
	  Map<Integer, String> categories = planService.getPlanCategories();
	  return new ResponseEntity<>(categories, HttpStatus.OK);
	}
  
	@PostMapping("/plan")
  public ResponseEntity<String> savePlan(@RequestBody Plan plan){
	  String responseMsg = "";
	  
	  boolean isSaved = planService.savePlan(plan);
	  
	  if(isSaved) {
		  responseMsg = "Plan saved";
	  }else {
		  responseMsg = "Plan not Saved";
	  }
	  
	  return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	  
  }
	
	@PutMapping("/plan/{planId}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isDelete = planService.savePlan(plan);
		String msg = "";
		
		if(isDelete) {
			msg = "Plan updated";
		}else {
			msg = "Plan not updated";
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	@GetMapping("/plan")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
 	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean isDelete = planService.deletePlanById(planId);
		String msg = "";
		
		if(isDelete) {
			msg = "Plan Deleted";
		}else {
			msg = "Plan not deleted";
		}
		
		return null;
		
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status){
		
		String msg = "";
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		
		if(isStatusChanged) {
			msg = "Status Changed";
			}else {
				msg = "Status not changed";
			}
		return new ResponseEntity<>(msg, HttpStatus.OK);
		}
	
}


