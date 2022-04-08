import React from "react";
import {
  Button,
  Col,
  Container,
  Form,
  FormControl,
  ListGroup,
  ListGroupItem,
  Nav,
  Navbar,
  NavDropdown,
  Row,
} from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const NaviMenu = () => {
  const navigator = useNavigate();

  const Logout = () => {
    if (typeof window !== "undefined") {
      localStorage.removeItem("jwt_key");
      localStorage.removeItem("role");
      navigator("/");
    }
  };

  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        style={{
          //height: "170px",
          //maxWidth: "540px",
          backgroundColor: "#502064", //#D3DEDC
        }}
        variant="dark"
      >
        <Container>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="me-auto">
              <Link className="nav-link" tag="a" to="/" action>
                {" "}
                Home{" "}
              </Link>
              {!localStorage.getItem("jwt_key") && (
                <div>
                  <ul class="navbar-nav ">
                    <li class="nav-item">
                      <Link class="nav-link" to="/login" action>
                        Login
                      </Link>
                    </li>
                    <li class="nav-item">
                      <Link class="nav-link" to="/register" action>
                        Register
                      </Link>
                    </li>
                  </ul>
                </div>
              )}
              {localStorage.getItem("jwt_key") && (
                <ul class="navbar-nav ">
                  <li class="nav-item">
                    <Link className="nav-link" tag="a" to="/allproducts" action>
                      {" "}
                      All Products{" "}
                    </Link>
                  </li>
                  {localStorage.getItem("role") === "[ROLE_Customer]" && (
                    <li class="nav-item">
                      <div>
                        <Link className="nav-link" tag="a" to="/mycart" action>
                          {" "}
                          MyCart{" "}
                        </Link>
                      </div>
                    </li>
                  )}
                  {localStorage.getItem("role") === "[ROLE_Customer]" && (
                    <li class="nav-item">
                      <div>
                        <Link className="nav-link" tag="a" to="/order" action>
                          {" "}
                          MyOrders{" "}
                        </Link>
                      </div>
                    </li>
                  )}
                </ul>
              )}
            </Nav>
            <Nav>
              {localStorage.getItem("jwt_key") && (
                <ul class="navbar-nav px-3  mb-lg-0 ">
                  <li className="nav-item">
                    <button
                      type="button"
                      class="btn btn-danger rounded btn-sm px-2 m-auto shadow-sm"
                      onClick={Logout}
                    >
                      Logout
                    </button>
                  </li>
                </ul>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default NaviMenu;
