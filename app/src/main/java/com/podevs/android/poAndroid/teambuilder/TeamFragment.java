package com.podevs.android.poAndroid.teambuilder;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.podevs.android.poAndroid.R;
import com.podevs.android.poAndroid.battle.ListedPokemon;
import com.podevs.android.poAndroid.poke.Team;
import com.podevs.android.poAndroid.pokeinfo.InfoConfig;
import com.podevs.android.poAndroid.pokeinfo.ItemInfo;
import com.podevs.android.poAndroid.pokeinfo.MoveInfo;

public class TeamFragment extends Fragment {
	ListedPokemon pokeList[] = new ListedPokemon[6];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.battle_teamscreen, container, false);
		
		for (int i = 0; i < 6; i++) {
			RelativeLayout whole = (RelativeLayout)v.findViewById(
					InfoConfig.resources.getIdentifier("pokeViewLayout" + (i+1), "id", InfoConfig.pkgName));
			pokeList[i] = new ListedPokemon(whole);
			whole.setTag(R.id.poke, i);
			
			whole.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Object tag = v.getTag(R.id.poke);
					
					if (tag != null) {
						int pos = ((Integer) tag);
						activity().editPoke(pos);
					}
				}
			});
		}
		updateTeam();
		
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}
	
	public void updateTeam() {
		Team team = activity().team;
		MoveInfo.forceSetGen(team.gen.num, team.gen.subNum);
        ItemInfo.setGeneration(team.gen.num);
		for (int i = 0; i < 6; i++) {
			pokeList[i].update(team.poke(i), true);
		}
	}
	
	private TeambuilderActivity activity() {
		return (TeambuilderActivity) getActivity();
	}

}
