package com.zpi.zpibackend;

import com.zpi.zpibackend.controller.ToDoListController;
import com.zpi.zpibackend.entity.*;
import com.zpi.zpibackend.entity.composite_key.CompanyAddressId;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.entity.dto.ToDoListDto;
import com.zpi.zpibackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ZpiBackendApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyTypeService companyTypeService;
	@Autowired
	private CompanyAddressService companyAddressService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EventService eventService;
	@Autowired
	private ToDoListService toDoListService;
	@Autowired
	private ToDoListTaskService toDoListTaskService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private ScheduleDetailService scheduleDetailService;
	@Autowired
	private EventPersonService eventPersonService;
	@Autowired
	private CostOrganizerService costOrganizerService;
	@Autowired
	private CostElementService costElementService;

	public static void main(String[] args) {
		SpringApplication.run(ZpiBackendApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		/*roleService.add(new Role(2,"Zarządca"));
		CompanyType companyType = new CompanyType(2,"ochrona");
		companyTypeService.add(companyType);
		companyService.add(new Company(1,companyType, "firma", "email","ok","ok"));
		companyService.add(new Company(2,companyType, "firmakolejna", "email","ok","ok"));
		companyService.add(new Company(3,companyType, "i nastepna","email","ok","ok"));
		Address address = new Address(1, "Miasto","Ulica", 1, 2);
		addressService.add(address);
		companyAddressService.add(new CompanyAddress(new CompanyAddressId(1,1)));
		companyAddressService.add(new CompanyAddress(new CompanyAddressId(2,1)));
		Person person = new Person(1,"email", "mamas", "dfads");
		Person person1 = new Person(2,"fad", "dfadf", "fdaf");
		personService.add(person);
		personService.add(person1);

		messageService.add(new Message(1,person,person1,"subject","koko", new Date(2012,12,11)));
		messageService.add(new Message(2,person1, person,"okoskoko", "oksfoskaodfk", new Date(3232,2,2)));
		Event event  = new Event(1,person,address,"opis", "bedziekozacki");
		eventService.add(event);

		ToDoList toDoList = new ToDoList(1, event,"kozaktytyl");
		toDoListService.add(toDoList);
		//toDoListTaskService.add(new ToDoListTask(1, toDoList,"jakisopis",new Date(12321,1,23), new Date(3231,1,1)));

		Schedule schedule = new Schedule(1, event, "ale schedule");
		scheduleService.add(schedule);
		scheduleDetailService.add(new ScheduleDetail(1,schedule,new Date(12321,1,23), new Date(3231,1,1), "jakis"));
		Role role = new Role(3, "ochroniarz");
		roleService.add(role);
		eventPersonService.add(new EventPerson(new EventPersonId(1,1), role));

		CostOrganizer costOrganizer = new CostOrganizer(1,event, "tytuł", new Date(2323,2,2));
		CostElement costElement = new CostElement(1, costOrganizer, 23.5, "ayayaya");
		CostElement costElement1 = new CostElement(2, costOrganizer, 34.5, "ooooo");
		costElement1.setParent(costElement);

		costOrganizerService.add(costOrganizer);
		costElementService.add(costElement);
		costElementService.add(costElement1);
	*/


	}
}
