/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core;

import shifu.thirdparty.JSONObject;

/**
 *
 * @author delwiv
 */
public interface ISerialisableJSON {

    public int readInt( String param, JSONObject JSONIn );

    public String readString( String param, JSONObject JSONIn );
}
