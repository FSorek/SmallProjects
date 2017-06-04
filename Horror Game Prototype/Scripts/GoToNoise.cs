using UnityEngine;
using System.Collections;

public class GoToNoise : MonoBehaviour {
	float yPos;
	GameObject target;
	// Use this for initialization
	void Start () {
		yPos = transform.position.y;
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		if (target != null) {
			this.transform.position = Vector3.MoveTowards (new Vector3 (transform.position.x, yPos, transform.position.z), target.transform.position, 0.4f * Time.deltaTime);

			//Quaternion.sle
		}
	}
	void OnTriggerEnter (Collider col)
	{
		if (col.gameObject.tag == "NoiseGen") {
			target = col.gameObject;
		}
	}
}
