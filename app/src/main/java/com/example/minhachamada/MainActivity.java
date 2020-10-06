package com.example.minhachamada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.example.minhachamada.adapters.AlunoAdapter;
import com.example.minhachamada.controllers.AlunoBancoController;


import com.example.minhachamada.entities.Aluno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvAlunos;
    ArrayList<Aluno> listaDeAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAlunos = (ListView)findViewById(R.id.lvAlunos);
        listaDeAlunos = new ArrayList<>();

    }

    public void atualizarLista(){
        AlunoAdapter adapter = new AlunoAdapter(this,listaDeAlunos);
        lvAlunos.setAdapter(adapter);
        Cursor cursor = new AlunoBancoController(this).getAll();
        listaDeAlunos.clear();
        while (cursor.moveToNext()){
            Aluno aluno = new Aluno(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("foto")));
            listaDeAlunos.add(aluno);
        }
        lvAlunos.deferNotifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        atualizarLista();
    }

    public void adicionar (View view) {
        Intent it = new Intent(this,AddActivity.class);
        startActivity(it);
    }
}
