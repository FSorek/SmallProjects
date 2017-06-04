using UnityEngine;
using System.Collections;

public class DoorInteraction : MonoBehaviour {
	public LayerMask interactLayer;
	private float dist = 0;
	private float vel = 100;
	public GameObject target;
	private UnityStandardAssets.Characters.FirstPerson.FirstPersonController kontrol;

	void Start()
	{
		kontrol = Camera.main.gameObject.transform.parent.gameObject.GetComponent<UnityStandardAssets.Characters.FirstPerson.FirstPersonController> ();
	}

	// Update is called once per frame
	void FixedUpdate () {
		Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

		RaycastHit _hit;
		if(Physics.Raycast(transform.position, ray.direction,out _hit, 4, interactLayer) && target == null) {
			if (Input.GetButtonDown ("Fire1")) {
				
				if (_hit.transform.gameObject.layer == LayerMask.NameToLayer ("RigidDoor")) {
					target = _hit.transform.gameObject;
					dist = Vector3.Distance(_hit.point, transform.position);
				}
			}
		}


			

		Rigidbody rb = null;
		if(target != null) rb = target.GetComponent<Rigidbody>();

		if (Input.GetButton ("Fire1") && rb) {
			target.GetComponent<SlowDownDoor> ().isDragged = true;
			if (Vector3.Distance (target.transform.position, transform.position) > 4) {
				End ();
				target = null;
				return;
			}
				JointMotor motor = target.GetComponent<HingeJoint> ().motor;
			if (motor.targetVelocity > 50)
				motor.targetVelocity = 50;
			if (motor.targetVelocity < -50)
				motor.targetVelocity = -50;

			//Camera.main.gameObject.transform.parent.gameObject.GetComponent<UnityStandardAssets.Characters.FirstPerson.FirstPersonController> ().enabled = false;
			kontrol.rotationLock = true;

			if (Input.GetAxis ("Mouse Y") < 0) {
					motor.targetVelocity += 2f;
					target.GetComponent<HingeJoint> ().motor = motor;
			}

			if (Input.GetAxis ("Mouse Y") > 0) {
				motor.targetVelocity -= 2f;
				target.GetComponent<HingeJoint> ().motor = motor;
			}
		}
		if (Input.GetButtonUp ("Fire1") && rb) {
			End();
		}

		if (target != null && !Input.GetButton ("Fire1")) {
			End ();
		}

	}
	void End()
	{
		JointMotor motor = target.GetComponent<HingeJoint> ().motor;
		target.GetComponent<SlowDownDoor> ().isDragged = false;
		//Camera.main.gameObject.transform.parent.gameObject.GetComponent<UnityStandardAssets.Characters.FirstPerson.FirstPersonController> ().enabled = true;
		kontrol.rotationLock = false;
		target.GetComponent<HingeJoint> ().motor = motor;
		target = null;
	}

}