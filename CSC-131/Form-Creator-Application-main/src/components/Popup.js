import React from "react";
import { useForm } from "react-hook-form";
import "./Popup.css";

function Popup(props) {
  const { register, handleSubmit } = useForm();
  const onSubmit = (data) => console.log(data);

  return props.trigger ? (
    <div className="popup">
      <div className="popup-inner">
        <form onSubmit={handleSubmit(onSubmit)}>
          {props.setShow.fullName
            ? [
                <label>Full Name:</label>,
                <input {...register("fullName")} placeholder="Full Name" />,
              ]
            : ""}

          {props.setShow.firstName
            ? [
                <label>First Name:</label>,
                <input {...register("firstName")} placeholder="First Name" />,
              ]
            : ""}

          {props.setShow.lastName
            ? [
                <label>Last Name:</label>,
                <input {...register("lastName")} placeholder="Last Name" />,
              ]
            : ""}

          {props.setShow.email
            ? [
                <label>Email:</label>,
                <input {...register("Email")} placeholder="email" />,
              ]
            : ""}

          {props.setShow.userName
            ? [
                <label>Username:</label>,
                <input {...register("userName")} placeholder="Username" />,
              ]
            : ""}

          <input type="submit" />
        </form>

        <button className="close-btn" onClick={() => props.setTrigger(false)}>
          close
        </button>
        {props.children}
      </div>
    </div>
  ) : (
    ""
  );
}

export default Popup;
