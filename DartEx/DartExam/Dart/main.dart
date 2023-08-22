import 'dart:convert';
import 'package:http/http.dart' as http;
import 'dart:io';

class Customer {
  final int? id;
  final DateTime dateOfBirth;
  final String fullName;
  final String address;
  final String phoneNumber;

  Customer({
    this.id,
    required this.dateOfBirth,
    required this.fullName,
    required this.address,
    required this.phoneNumber,
  });

  factory Customer.fromJson(Map<String, dynamic> json) {
    return Customer(
      id: json['id'],
      dateOfBirth: DateTime.parse(json['dateOfBirth']),
      fullName: json['fullName'],
      address: json['address'],
      phoneNumber: json['phoneNumber'],
    );
  }
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'dateOfBirth': dateOfBirth.toIso8601String(),
      'fullName': fullName,
      'address': address,
      'phoneNumber': phoneNumber,
    };
  }
}

Future<List<Customer>> getAllCustomers() async {
  final customerListUrl = Uri.parse('http://localhost:8080/api/customers/getAll');
  final customerListResponse = await http.get(customerListUrl);
  List<Customer> customers = [];

  if (customerListResponse.statusCode == 200) {
    final List<dynamic> customerJsonList = json.decode(customerListResponse.body);
    customers = customerJsonList.map((json) => Customer.fromJson(json)).toList();

    for (var customer in customers) {
      print('Customer ID: ${customer.id}');
      print('Full Name: ${customer.fullName}');
      print('Date of Birth: ${customer.dateOfBirth}');
      print('Address: ${customer.address}');
      print('Phone Number: ${customer.phoneNumber}');
      print('---');
    }
  } else {
    print('Failed to fetch customer list');
  }

  return customers;
}


Future<Customer> addNewCustomer(Customer customer) async {
  final addCustomerUrl = Uri.parse('http://localhost:8080/api/customers/create');
  final newCustomer = Customer(
    id: 0,
    dateOfBirth: customer.dateOfBirth,
    fullName: customer.fullName,
    address: customer.address,
    phoneNumber: customer.phoneNumber,
  );

  final addCustomerResponse = await http.post(
    addCustomerUrl,
    headers: {'Content-Type': 'application/json'},
    body: json.encode(newCustomer.toJson()),
  );

  if (addCustomerResponse.statusCode == 200) {
    print('New customer added successfully');
  } else {
    print('Failed to add new customer');
  }
  return customer;
}
void main() async {

  bool shouldExit = false;
   while (!shouldExit) {
    print('Menu Options:');
    print('1. Get all Customer');
    print('2. Add a Customer');
    print('4. Exit');

    stdout.write('Enter your choice: ');
    String input = stdin.readLineSync() ?? '';

    switch (input) {
      case '1':
        List<Customer> allCustomers = await getAllCustomers();
        for (var customer in allCustomers) {
          printCustomerInfo(customer);
        }
        break;
      case '2':
        print('Enter Customer Information:');
   
  stdout.write('Date of Birth (YYYY-MM-DD): ');
  DateTime dateOfBirth = DateTime.parse(stdin.readLineSync() ?? '');

  stdout.write('Full Name: ');
  String fullName = stdin.readLineSync() ?? '';

  stdout.write('Address: ');
  String address = stdin.readLineSync() ?? '';

  stdout.write('Phone Number: ');
  String phoneNumber = stdin.readLineSync() ?? '';

  Customer newCustomer = Customer(
    dateOfBirth: dateOfBirth,
    fullName: fullName,
    address: address,
    phoneNumber: phoneNumber,
  );
     Customer addedCustomer = await addNewCustomer(newCustomer);
        printCustomerInfo(addedCustomer);
        break;
      case '4':
        print('Exiting...');
        shouldExit = true;
        break;
      default:
        print('Invalid choice. Please enter a valid option.');
    }
    print('\n'); 
  }
  
 
}
 void printCustomerInfo(Customer customer) {
  print('Customer ID: ${customer.id}');
  print('Full Name: ${customer.fullName}');
  print('Date of Birth: ${customer.dateOfBirth}');
  print('Address: ${customer.address}');
  print('Phone Number: ${customer.phoneNumber}');
  print('---');
}