The 'MARS ROVER' API do two main functions.
1)From 'Rover' current coordinates, directin , string of commands and the coordinates of the obstacles
	 --> Return new coordinates after follow the commands  
  to try it follow this steps
  a)Open the 'Mars Rover' on your IDE and Run it  
  b)Use "http:// + localhost +(#localHost eg:8080) +/api/excuteCommands" 
  c)Put you input like that -change the values between the brackets- 
     
	{
    	   "currentX":(1),
    	   "currentY":(2),
   	   "currentDirection":"(EAST)",
    	   "commands": "(FFF)",
    	   "obstacles" :
    	   [
        	{
		   "location": {"x":(1),"y":(1)} ,
	  	   "location": {"x":(2),"y":(2)} ,
	  	    "location": {"x":(3),"y":(3)} .......
		}
    	   ]
	}
2)From 'Rover' current coordinates, directin, heading coordinates and the coordinates of the obstacles
	--> Return string of commands that ensure safely movement without any collision
  to try it follow this steps
  a)Open the 'Mars Rover' on your IDE and Run it  
  b)Use "http:// + localhost +(#localHost eg:8080) +/api/safelyMovement" 
  c)Put you input like that -change the values between the brackets- 
	
	{
    	   "currentX":(4),
           "currentY":(2),
           "currentDirection":"(EAST)",
           "headingX":(5),
           "headingY":(3),
   	   "obstacles" :
    	   [
       		{
		   "location": {"x":(1),"y":(1)} ,
	  	   "location": {"x":(2),"y":(2)} ,
	  	   "location": {"x":(3),"y":(3)} .......
		}   ]
	}

