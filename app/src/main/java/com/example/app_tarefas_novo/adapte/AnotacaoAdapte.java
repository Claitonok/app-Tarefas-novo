package com.example.app_tarefas_novo.adapte;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.AnotacaoDAO;
import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.MainActivity;
import com.example.app_tarefas_novo.R;
import com.example.app_tarefas_novo.model.Anotacao;
import com.example.app_tarefas_novo.model.Tarefa;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AnotacaoAdapte extends RecyclerView.Adapter<AnotacaoViewHolder> {

    private List<Anotacao> anotacaoList = new ArrayList<>();

    public AnotacaoAdapte(List<Anotacao> anotacaoList) {
        this.anotacaoList = anotacaoList;
    }

    public AnotacaoAdapte() {}

    public void setListaAnotacao(List<Anotacao> anotacaoList) {
        this.anotacaoList = anotacaoList;
    }


    @NonNull
    @Override
    public AnotacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemAnotacao = LayoutInflater.from(parent.getContext()).inflate(R.layout.anotacao_liste_r , parent, false);
        return new AnotacaoViewHolder(itemAnotacao);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull AnotacaoViewHolder holder, int position) {


        Anotacao anotacao =  anotacaoList.get(position);
        holder.txtAnotacao.setText(anotacao.getAnotacao());

        holder.btDeleteAnotacao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir essa anotacao?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AnotacaoDAO dao = new AnotacaoDAO(view.getContext());
                                boolean sucesso = dao.deletarAnotacoes(anotacao.getId());

                                if(sucesso) {
                                    removerAnotacao(anotacao);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });


        holder.btInicioAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obter o contexto a partir do holder
                Context context = holder.itemView.getContext();

                Toast.makeText(context,"Redirecionando!!!", Toast.LENGTH_LONG).show();

                // Criar um Intent para iniciar a nova Activity
                Intent intent = new Intent(context, MainActivity.class);

                // Iniciar a nova Activity
                context.startActivity(intent);
            }
        });


            //Opção Editar atraves de um click no botao ImageButton!!!
            Anotacao an = anotacaoList.get(position);
            holder.txtAnotacao.setText(an.getAnotacao());
            holder.btEditarAnotacao.setOnClickListener(new View.OnClickListener() {
                @Override
             public void onClick(View view) {

                if (!an.toString().isEmpty()){

                    //Minha variavel vindo do Front-End
                    String nAnotacao = an.toString();

                    //executar a ação salvar
                    AnotacaoDAO anotacaoDAO = new AnotacaoDAO(view.getContext());

                    Anotacao ano = new Anotacao();
                    ano.setAnotacao(nAnotacao);

                    boolean sucesso = anotacaoDAO.atualizarAnotacoes(ano);

                    if(sucesso){
                        Anotacao anotacao = anotacaoDAO.retornarUltimo_Anotacao();

                        adicinar(anotacao);

                        Toast.makeText(view.getContext(),
                                "Sucesso ao editar a anotacao",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(),
                                "Erro ao editar!",
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Ops!!")
                            .setMessage("Por favor consulte a equipe tecnica?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(view.getContext(),
                                            "Por favor consulte a equipe tecnica?!",
                                            Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .create()
                            .show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void adicinar(Anotacao anotacao){
        anotacaoList.add(anotacao);
        notifyItemInserted(getItemCount());
    }

    public void removerAnotacao(Anotacao anotacao){
        int id = anotacaoList.indexOf(anotacao);
        anotacaoList.remove(id);
        notifyItemRemoved(id);
    }



}
