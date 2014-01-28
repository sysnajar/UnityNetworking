#pragma strict

var speedForChange : int; 
private var myTCP  : s_TCP; 


function Awake() {
 myTCP = gameObject.AddComponent(s_TCP);
}


function Start () {

	 myTCP.setupSocket();
}

function handleKeyboard()
{
	if(Input.GetButtonDown("RotateLeftBtn"))
	   ModelController.rotateDir = 1;
	   
	   
	if(Input.GetButtonDown("RotateRightBtn"))
	   ModelController.rotateDir = 2; 
	   
	if(Input.GetButtonDown("StopRotateBtn"))
	   {ModelController.rotateDir = 0;
	    var cmd  = myTCP.readSocket();
	     Debug.Log("Server command: " + cmd);
	   }
	   
	  
	   

}
function Update () { 

	handleKeyboard();
	 
	if(ModelController.rotateDir==1)
	   transform.Rotate(0, speedForChange*Time.deltaTime,0);
	   
	if(ModelController.rotateDir==2)
	   transform.Rotate(0, -speedForChange*Time.deltaTime,0);   
	   
	   
	       
	   
}