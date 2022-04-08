import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import login from "../login.jpg";
import { Button, Container, Form, FormGroup, Input } from "reactstrap";
import { getORdersfromDB } from "./order";

const Login = () => {
  const [User, setUser] = useState({});

  const handleForm = (event) => {
    console.log(User);
    postDataToServer(User);
    event.preventDefault();
  };

  const nav = useNavigate();

  const postDataToServer = (data) => {
    axios.post(`http://localhost:5005/main/authenticate`, data).then(
      (Response) => {
        console.log(Response.data);
        localStorage.setItem("jwt_key", Response.data.jwt);
        localStorage.setItem("role", Response.data.role);
        localStorage.setItem("user", JSON.stringify(Response.data));
        getORdersfromDB(
          JSON.parse(localStorage.getItem("user")).userId.profileId
        );
        nav("/allproducts");
      },
      (Error) => {
        console.log(Error);
        displayError();
      }
    );
  };

  const msg = document.getElementById("msg");

  const displayError = () => {

    msg.style.display = "block";

  };

  return (
    <div className="m-auto  shadow w-50 border mt-3 text-center p-3">
      <Form onSubmit={handleForm} >
        <h1 className="text-center my-3">
          <img src={login} style={{ width: "120px", height: "150px" }}></img>
        </h1>

        <FormGroup>
          <label>Full Name </label>
          <Input
            type="text"
            placeholder="Full Name"
            name="Full Name"
            id="Full Name"
            onChange={(event) => {
              setUser({ ...User, username: event.target.value });
            }}
          />
        </FormGroup>

        <FormGroup>
          <label>Password </label>
          <Input
            type="Password"
            placeholder="Password"
            name="Password"
            id="Password"
            onChange={(event) => {
              setUser({ ...User, password: event.target.value });
            }}
          />
        </FormGroup>

        <div
          class="alert alert-danger"
          role="alert"
          id="msg"
          style={{ display: "none" }}
        >
          Invalid username or password
        </div>

        <Container>
          <Button className="center" type="submit" color="primary">
            Login
          </Button>
        </Container>
      </Form>
    </div>
  );
};

export default Login;
