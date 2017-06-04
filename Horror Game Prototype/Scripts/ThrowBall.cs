using UnityEngine;
using System.Collections;

public class ThrowBall : MonoBehaviour {
	public GameObject projectile;
	//float bulletSpeed= 20;

	void Update () {
		// Put this in your update function
		if (Input.GetButtonDown("Fire1")) {

			// Instantiate the projectile at the position and rotation of this transform
			GameObject clone;
			clone = (GameObject)Instantiate(projectile, transform.position, transform.rotation);
			// Add force to the cloned object in the object's forward direction
			clone.GetComponent<Rigidbody>().AddForce(clone.transform.forward * 1000);

		}
	}
}
