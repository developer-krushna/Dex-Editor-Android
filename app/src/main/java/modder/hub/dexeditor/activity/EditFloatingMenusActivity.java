/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-26, developer-krushna
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of developer-krushna nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


 *     Please contact Krushna by email mt.modder.hub@gmail.com if you need
 *     additional information or have any questions
 */


package modder.hub.dexeditor.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.updateSoraMenu.ItemMoveCallback;
import modder.hub.dexeditor.updateSoraMenu.RecyclerViewAdapter;
import modder.hub.dexeditor.updateSoraMenu.StartDragListener;

/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/

public class EditFloatingMenusActivity extends AppCompatActivity implements StartDragListener {

    private ItemTouchHelper touchHelper;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.floating_menus_customize);
        initialize(_savedInstanceState);
        initializeLogic();
    }


    private void initialize(Bundle _savedInstanceState) {
        Toolbar _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initializeLogic() {
        setTitle("Edit floating menus");
        Toast.makeText(this, "Use the menu logo to slide up and down to change the menu sequence", Toast.LENGTH_LONG).show();
        _load_shortcut_keys();
    }


    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);

    }


    public void _load_shortcut_keys() {
        SharedPreferences prefs = getSharedPreferences("editor_prefs", Context.MODE_PRIVATE);
        String jsonConfig = prefs.getString("menu_order", null);
        ArrayList<HashMap<String, Object>> menu_items = new Gson().fromJson(jsonConfig, new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        try {
            RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(menu_items, this, this);
            ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
            touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception ignored) {

        }
    }

}
