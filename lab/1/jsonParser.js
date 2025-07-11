const { update } = require("tar");
const data = require("./data.json");
const fs = require("fs").promises;

function jsonParser() {
	try {
		const parsedData = JSON.parse(JSON.stringify(data));
		return parsedData;
	} catch (error) {
		console.error("Error parsing JSON data:", error);
		return null;
	}
}

const parsedData = jsonParser();

console.log("\nPrint the name of the company and the location");
console.log(`Company: ${parsedData.company}`);
console.log(`Location: ${parsedData.location}`);

console.log("\nAccess and print the list of employees");
console.log("Employees:");
parsedData.employees.map((employee) => {
	console.log(`Name: ${employee.name}, Age: ${employee.age}, Department: ${employee.department}`);
});

console.log("\nPrint the department of the employee named Alice");
const alice = parsedData.employees.find((employee) => employee.name === "Alice");
if (alice) {
	console.log(`Alice's Department: ${alice.department}`);
} else {
	console.log("Alice not found in the employee list.");
}

console.log("\nModifying the JSON Data to a new JSON file called updated_data.json - Adding a new employee");
const newEmployee = {
	name: "Sarah",
	age: 28,
	department: "Marketing",
};

async function addNewEmployeeToJSON() {
	try {
		const updatedData = { ...parsedData };
		updatedData.employees.push(newEmployee);

		await fs.writeFile("updated_data.json", JSON.stringify(updatedData, null, 2), "utf8");

		console.log("Files created successfully");
	} catch (err) {
		console.error("Error writing files:", err);
	}
}
addNewEmployeeToJSON();

console.log("\nFilter and print employees older than 30:");
const olderEmployees = parsedData.employees.filter((employee) => employee.age > 30);
olderEmployees.forEach((employee) => {
	console.log(`Name: ${employee.name}, Age: ${employee.age}, Department: ${employee.department}`);
});
