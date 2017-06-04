using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class KillfeedItem : MonoBehaviour {
    [SerializeField]
    Text player1;
    [SerializeField]
    Text player2;
	// Use this for initialization
	public void Setup(string player, string source)
    {
        player1.text = "<b>" + source + "</b>";
        player2.text = "<b>" + player + "</b>";
    }
}
