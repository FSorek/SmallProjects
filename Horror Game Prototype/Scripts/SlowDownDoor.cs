using UnityEngine;
using System.Collections;

public class SlowDownDoor : MonoBehaviour {
	JointMotor motor;
	public bool isDragged;
	// Use this for initialization
	void Start () {
		isDragged = false;
		motor =  GetComponent<HingeJoint> ().motor;
	}
	
	// Update is called once per frame
	void FixedUpdate ()
	{
		if (!isDragged) {
			motor =  GetComponent<HingeJoint> ().motor;
			if (motor.targetVelocity > 0) {
				motor.targetVelocity -= 0.5f;
				GetComponent<HingeJoint> ().motor = motor;
			} else if (motor.targetVelocity < 0) {
				motor.targetVelocity += 0.5f;
				GetComponent<HingeJoint> ().motor = motor;
			}
		}	
	}
}
