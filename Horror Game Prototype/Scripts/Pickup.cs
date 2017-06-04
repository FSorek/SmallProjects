using UnityEngine;
using System.Collections;

public class Pickup : MonoBehaviour {
	public Transform empty;
	public LayerMask interactLayer;
	private float dist = 0;

	private Transform target;

	// Update is called once per frame
	void Update () {
		Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

		RaycastHit h;
		if(Physics.Raycast(transform.position, ray.direction, out h, interactLayer) && target == null) {
			if(Input.GetButtonDown("Fire1")) {
				dist = Vector3.Distance(h.point, transform.position);
				empty.position = transform.position+(ray.direction.normalized * dist);

				target = h.transform;
			} 
		}

		Rigidbody rb = null;

		if(target != null) rb = target.GetComponent<Rigidbody>();

		if(Input.GetButton("Fire1") && rb) {
			rb.isKinematic = true;
			target.transform.parent = empty;
			empty.position = transform.position + (ray.direction.normalized * dist);
		}

		if(Input.GetButtonUp("Fire1") && rb) {
			rb.isKinematic = false;
			target.transform.parent = null;
			target = null;
		}
	}
}