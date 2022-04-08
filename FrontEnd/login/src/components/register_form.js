import React, { useEffect, useState } from "react";
import { Container, Form, FormGroup, Input, Label } from "reactstrap";
import { Button, Dropdown, DropdownButton, Toast } from 'react-bootstrap';
import axios from "axios";
import Website from "../api/website";
import {toast} from "react-toastify";
import "../components/Home.css"

function Register () {

  useEffect(() => {
    document.title = "Register";
  }, []);

  const [Profile, setProfile] = useState({});
  const [Address, setAddress] = useState([]);
  var userAddressList = [];

  const handleForm = (event) => {
    console.log(Profile);
    postDataToServer(Profile);
    event.preventDefault();
  };

  const postDataToServer = (data) => {
    if(data.role === "Merchant")
    {
      axios.post(`${Website}/profile/addMerchant`, data).then(
        (Response) => {
          alert(" Merchant role added successfully")
        },
        (Error) => {
          console.log(Error);
          alert("Error");
        }
      );
    }
    else if(data.role === "Delivery Agent")
    {
      axios.post(`${Website}/profile/addDeliveryAgent`, data).then(
        (Response) => {
          alert(" Delivery role added successfully")
        },
        (Error) => {
          console.log(Error);
          alert("Error");
        }
      );
    }
    else
    {
      axios.post(`${Website}/profile/addCustomer`, data).then(
        (Response) => {
          alert(" Customer role added successfully")
        },
        (Error) => {
          console.log(Error);
          alert("Error");
        }
      );
    }

  }

  return (
    <div className="m-auto w-50 border shadow-lg mt-3 text-center p-3">
       <Form onSubmit={handleForm}>
        <h1>Register Here</h1>

         <FormGroup>
          <label>Name </label>
          <Input
            required 
            type="text" 
            placeholder="Name" 
            name="text" 
            id="Name" 
            onChange={(event) => {
              setProfile({...Profile, fullName: event.target.value});
            }}
          />
        </FormGroup>

        <FormGroup>
          <label>EmailId </label>
          <Input 
            type="Email" 
            placeholder="Email" 
            name="Email" 
            id="Email" 
            onChange={(event) => {
              setProfile({...Profile, emailId: event.target.value});
            }}
          />
        </FormGroup>

        <FormGroup>
          <label>Password </label>
          <Input
            required
            type="Password"
            placeholder="Password"
            name="Password"
            id="Password"
            onChange={(event) => {
              setProfile({...Profile, password: event.target.value});
            }}
          />
        </FormGroup>

        <FormGroup>
            <label> Gender </label>
            <Input 
                type="Gender"
                placeholder="Gender"
                name="Gender"
                id="Gender"
                onChange={(event) => {
                  setProfile({...Profile, gender: event.target.value});
                }}
            />
        </FormGroup>

        <FormGroup>
            <label> Date of Birth </label>
            <Input 
                type="date"
                placeholder="year-month-date"
                name="DateofBirth"
                id="DateofBirth"
                onChange={(event) => {
                  setProfile({...Profile, dateofBirth: event.target.value});
                }}
            />
        </FormGroup>

        <FormGroup>
            <label> Contact </label>
            <Input
                type="text"
                placeholder="Mobile Number"
                name="Contact"
                id="Contact"
                maxLength="10"
                onChange={(event) => {
                  setProfile({...Profile, mobilenumber: event.target.value});
                }} 
            />
        </FormGroup>

        <FormGroup>
          <Label for="dropdown-basic-button">Role</Label>
          <Input 
            type="select" 
            name="Role" id="dropdown-basic-button" 
            onChange={(event) => {
                  setProfile({...Profile, role: event.target.value});
                }}> 
            <option>Customer</option>
            <option>Merchant</option>
            <option>Delivery Agent</option>
          </Input>
        </FormGroup>

        <FormGroup>
            <label> House Number </label>
            <Input
                type="number"
                placeholder="House Number"
                name="House Number"
                id="House"
                maxLength="3"
                onChange={(event) => {
                  setAddress({...Address, houseNumber: event.target.value});
                }}
            />
        </FormGroup>

        <FormGroup>
            <label> Street Name </label>
            <Input
                type="text"
                placeholder="Street Name"
                name="Street Name"
                id="Street"
                onChange={(event) => {
                  setAddress({...Address, streetName: event.target.value});
                }} 
            />
        </FormGroup>

        <FormGroup>
            <label> Colony Name </label>
            <Input
                type="text"
                placeholder="Colony Name"
                name="Colony Name"
                id="Colony"
                onChange={(event) => {
                  setAddress({...Address, colonyName: event.target.value});
                }} 
            />
        </FormGroup>

        <FormGroup>
            <label> City </label>
            <Input
                type="text"
                placeholder="City"
                name="City"
                id="City"
                onChange={(event) => {
                  setAddress({...Address, city: event.target.value});
                }} 
            />
        </FormGroup>

        <FormGroup>
            <label> State </label>
            <Input
                type="text"
                placeholder="State"
                name="State"
                id="State"
                onChange={(event) => {
                  setAddress({...Address, state: event.target.value});
                }} 
            />
        </FormGroup>

        <FormGroup>
            <label> Pincode </label>
            <Input
                required
                type="text"
                placeholder="Pincode"
                name="Pincode"
                id="Pincode"
                maxLength="7"
                onChange={(event) => {
                  setAddress({...Address, pincode: event.target.value});
                  console.log(Address);
                  userAddressList.push(Address);
                  setProfile({ ...Profile, userAddresses : userAddressList})
                }} 
            />
        </FormGroup>

        <Button 
          className="center" 
          type="submit" 
          color="success"
        > 
          Register
        </Button>
  
      </Form> 
    </div>

  );
};

export default Register;