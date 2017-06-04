using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Killfeed : MonoBehaviour {

    [SerializeField]
    private GameObject killfeedItemPrefab;
	void Start () {
        GameManager.Instance.onPlayerKilledCallback += OnKill;
	}

    public void OnKill(string player, string source)
    {
        GameObject go = Instantiate(killfeedItemPrefab, this.transform);
        go.GetComponent<KillfeedItem>().Setup(player, source);
        go.transform.SetSiblingIndex(0);
        Destroy(go, 4f);
    }
}
