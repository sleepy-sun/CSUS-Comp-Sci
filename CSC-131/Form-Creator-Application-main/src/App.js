import Popup from "./components/Popup";
import { useState } from "react";
import { useForm } from "react-hook-form";

function App() {
  const [style,setStyle] = useState() //use this to add color to buttons when clicked
  const [buttonPopup, setButtonPopup] = useState(false);
  const [show, setShow] = useState({
    fullName: false,
    firstName: false,
    lastName: false,
    email: false,
    userName: false,

  });

  const handleClick = (e) => {
    setShow({...show,   [e.target.id]: !show[e.target.id],
    });
  };





  return (
    <div className="App">
      <main>
        <h1>Form Generator</h1>
        <p>Select form elements</p>
        <br></br>
        <button id='fullName'  onClick={handleClick}>Full Name</button>
        <button id='firstName' onClick={handleClick}>First Name</button>
        <button id='lastName' onClick={handleClick}>Last Name</button>
        <button id='email' onClick={handleClick}>Email</button>
        <button id='userName' onClick={handleClick}>Username</button>


        <button onClick={() => setButtonPopup(true)}>Generate</button>
      </main>
      <Popup trigger={buttonPopup} setShow={show} setTrigger={setButtonPopup}>

        <p>popup triggered</p>
      </Popup>
    </div>
  );
}

export default App;

{
  /* <main>
<h1>React Popups</h1>
<br></br>
<button onClick={() => setButtonPopup(true)}> Open Popup</button>
</main>
<Popup trigger={false} setTrigger={setButtonPopup}>
  <h3>my popup</h3>
  <p>this is my button triggered popup</p>
</Popup>

 */
}
