static var rotateDir : int;

// Use this for initialization
function Start () 
{
rotateDir = 0;
}

function OSCMessageReceived(message : OSC.NET.OSCMessage){ 
		var address = message.Address;
		var args :ArrayList = message.Values; 
		
		Debug.Log("OSCMessageReceived : address = "+address);
		
		if(address == "/rotate")
		  {
		     var flag = args[0].ToString();
		     if(flag=="L")
		        rotateDir = 1;
		     else  
		        if(flag=="R")
		          rotateDir = 2;
		          else
		            rotateDir = 0; 
		       
		     
		  }
		
		 
		
		
	}
	
// Update is called once per frame
function Update () {

    
}

