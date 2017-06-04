using UnityEngine;
using System.Collections;

public class PickupObject : MonoBehaviour {
	GameObject mainCamera;
	public bool carrying;
	GameObject carriedObject;
	public float distance;
	public float smooth;
	// Use this for initialization
	void Start () {
		mainCamera = GameObject.FindWithTag("MainCamera");
	}

	// Update is called once per frame
	void FixedUpdate () {
		if(carrying) {
			carry(carriedObject);
			checkDrop();
		} else {
			pickup();
		}
	}

	void rotateObject() {
		carriedObject.transform.Rotate(5,10,15);
	}

	void carry(GameObject o) {
		
		o.transform.position = Vector3.Lerp (o.transform.position, mainCamera.transform.position + mainCamera.transform.forward * distance, Time.deltaTime * smooth);
		o.transform.rotation = Quaternion.identity;
	}

	void pickup() {
		if(Input.GetMouseButton(0)) {
			int x = Screen.width / 2;
			int y = Screen.height / 2;

			Ray ray = mainCamera.GetComponent<Camera>().ScreenPointToRay(new Vector3(x,y));
			RaycastHit hit;
			if(Physics.Raycast(ray, out hit)) {
				Carry p = hit.collider.GetComponent<Carry>();
				if(p != null) {
					carrying = true;
					carriedObject = p.gameObject;
					p.gameObject.GetComponent<Rigidbody>().useGravity = false;
					//p.gameObject.GetComponent<Light> ().enabled = true;
					p.gameObject.GetComponent<Carry> ().isCarried = true;
				}
			}
		}
	}

	void checkDrop() {
		if(Input.GetMouseButton(0)) {
			dropObject();
		}
	}

	void dropObject() {
		carrying = false;
		//carriedObject.gameObject.rigidbody.isKinematic = false;
		carriedObject.gameObject.GetComponent<Rigidbody>().useGravity = true;
		//carriedObject.gameObject.GetComponent<Light> ().enabled = false;
		carriedObject.gameObject.GetComponent<Carry> ().isCarried = false;
		carriedObject = null;
	}
}