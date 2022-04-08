import React from "react";
import { Link } from "react-router-dom";
const Menu = () => {
  return (
    <div>
      <ul class="list-group">
        <Link class="list-group-item with active"  to="/addproduct" action>
            {" "}
            Add Product{" "}
        </Link>
        <Link class="list-group-item"  to="/updateproduct" action>
            {" "}
            Modify Product{" "}
        </Link>        
      </ul>
    </div>
  );
};