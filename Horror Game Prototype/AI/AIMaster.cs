using UnityEngine;
using System.Collections;
using System.Collections.Generic;
public class AIMaster : MonoBehaviour {


	[HideInInspector]
	public List<GameObject> Waypoints;
	[HideInInspector]
	public bool isMoving;

	public bool isPatroling;
	[HideInInspector]
	public bool needDestination;

	private UnityEngine.AI.NavMeshAgent agent;
	private int i;
	// Use this for initialization
	void Start () {
		foreach (GameObject n in GameObject.FindGameObjectsWithTag("Waypoint")) {
			Waypoints.Add (n);
		}
		agent = GetComponent<UnityEngine.AI.NavMeshAgent> ();
		isMoving = false;
		i = 0;
	}
	
	// Update is called once per frame
	void Update () {
		if (isPatroling) {
			if (agent.remainingDistance == 0) {
				if (i >= Waypoints.Count-1)
					i = 0;
				isMoving = false;
				Debug.Log (Waypoints [i].name);
				agent.SetDestination (Waypoints [i].transform.position);
				i++;
			}

			if (!isMoving) {
				agent.Resume ();

				isMoving = true;
			}
		}
	}
}
