/*
 * Starter file 
 */
(function() {
  "use strict";

  /**
   * The starting point in our program, setting up a listener
   * for the "load" event on the window, signalling the HTML DOM has been constructed
   * on the page. When this event occurs, the attached function (init) will be called.
   */
  window.addEventListener("load", init);

  /**
   * TODO: Write a function comment using JSDoc.
   */
  function init() {
    // Note: In this function, we usually want to set up our event handlers
    // for UI elements on the page.
    console.log("Window loaded!");
    document.getElementById("encrypt-it").addEventListener("click", handleClick);
		document.getElementById("reset").addEventListener("click", reset);
		document.getElementById("big").addEventListener("click", fontSize);
		document.getElementById("normal").addEventListener("click", fontSize);
		document.getElementById("all-caps").addEventListener("change", caps);

  }

  // Add any other functions in this area (you should not implement your
  // entire program in the init function, for similar reasons that
  // you shouldn't write an entire Java program in the main method).

  function handleClick() {
    var input = document.getElementById('input-text').value;
    var output = document.getElementById('result');
    output.innerHTML = encrypt(input);
  }

  // modified a caeser cipher to get the job done
  function encrypt(input) { 
    var output = '';
    for (var i = 0; i < input.length; i++) {
      var c = input.charCodeAt(i);
      if (c >= 65 && c <= 90) {
        output += String.fromCharCode((c - 65 + 1) % 26 + 65);
      } else if (c >= 97 && c <= 122) {
        output += String.fromCharCode((c - 97 + 1) % 26 + 97);
      } else {
        output += input.charAt(i);
      }
    }
    return output;
  }
  

  function fontSize() {
    if (document.getElementById("normal").checked) {
      document.getElementById("result").style.fontSize="12pt";
    } else {
      document.getElementById("result").style.fontSize="24pt";
    }
  }

  function caps() {
		if (document.getElementById("all-caps").checked) {
			document.getElementById("result").innerText = document.getElementById("result").innerText.toUpperCase();
		} else {
			document.getElementById("result").innerText = document.getElementById("result").innerText.toLowerCase();
		}
	}

  function reset(event) {
    document.getElementById("input-text").value = "";
    document.getElementById("result").innerText = "";
    document.getElementById("result").style.fontSize = "12pt";
    document.getElementById("all-caps").checked = false;
		document.getElementById("normal").checked = true;
  }

})();
