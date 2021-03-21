
# SlotAllocation
A simple service API written in Spring Boot Java that will allow a client to manage their parking slot allocation.

## Usage
There are two api calls that allow allocate and deallocate parking slots. Number of slots to be available in the system is read as a config property on load. 
The parking rate per hour and minimum parking hours can be configured in the properties file.

1. A post method /parking-service/v1/allocations that creates a new allocation for the current timestamp. 
2. A put method /parking-service/v1/allocations/{allocationId} does the deallocation of the slot and calculates the total hours and amount to be charged.

## Requirements
   This application uses Maven as the build tool and requires JDK 1.8 .
   
## License
© Deepthi Antony K

Licensed under [MIT License](LICENSE)

