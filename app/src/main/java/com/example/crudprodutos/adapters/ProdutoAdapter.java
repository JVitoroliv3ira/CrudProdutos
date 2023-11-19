package com.example.crudprodutos.adapters;

// ProdutoAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudprodutos.R;
import com.example.crudprodutos.models.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> produtos;

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.tvCodigoProduto.setText(produto.getCodigo());
        holder.tvNomeProduto.setText(produto.getNome());
        holder.tvDescricaoProduto.setText(produto.getDescricao());
        holder.tvQuantidade.setText(String.format("%d", produto.getQuantidade()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigoProduto;
        TextView tvNomeProduto;
        TextView tvDescricaoProduto;
        TextView tvQuantidade;

        ProdutoViewHolder(View view) {
            super(view);
            tvCodigoProduto = view.findViewById(R.id.list_codigo_produto);
            tvNomeProduto = view.findViewById(R.id.list_nome_produto);
            tvDescricaoProduto = view.findViewById(R.id.list_descricao_produto);
            tvQuantidade = view.findViewById(R.id.list_estoque_produto);
        }
    }
}
