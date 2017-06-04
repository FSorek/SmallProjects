using UnityEngine;
using System.Collections;

public class SphereSlotScript : MonoBehaviour {

	public GameObject BallPrefab;
	public GameObject DoorToOpen;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnCollisionEnter(Collision col)
	{
		if (col.collider.gameObject == BallPrefab) {
			GameObject.FindWithTag ("Player").GetComponent<PickupObject> ().carrying = false;
			GameObject theBall = col.gameObject;
			Destroy (theBall);
			this.transform.parent.GetChild (2).gameObject.GetComponent<MeshRenderer> ().enabled = true;
			this.transform.parent.GetChild (2).gameObject.GetComponent<ParticleSystem> ().startSpeed = 2;
			if (!this.transform.parent.GetChild (2).gameObject.GetComponent<AudioSource> ().isPlaying)
				this.transform.parent.GetChild (2).gameObject.GetComponent<AudioSource> ().Play ();
			if (DoorToOpen != null) {
				DoorToOpen.GetComponent<DoorScript> ().open = true;
			}
		}
	}
}
