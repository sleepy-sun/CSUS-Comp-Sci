function hola() {
    alert("The text is going to change!");
}

function bigger() {
    hola();
    document.getElementById("txtinput").style.fontSize = "24pt";
}

function fancy() {
    hola();
    document.getElementById("txtinput").style.fontWeight = "bold";
    document.getElementById("txtinput").style.color = "blue";
    document.getElementById("txtinput").style.textDecoration = "underline";
}

function boring() {
    document.getElementById("txtinput").style.fontWeight = "normal";
    document.getElementById("txtinput").style.color = "black";
    document.getElementById("txtinput").style.textDecoration = "none";
}

function moo() {
    document.getElementById("txtinput").value = document.getElementById("txtinput").value.toUpperCase();
   var str = document.getElementById("txtinput");
   var parts = str.value.split(".");
   str.value = parts.join("-Moo");
}