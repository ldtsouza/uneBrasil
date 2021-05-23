package br.ucsal.pdm.unebrasil.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;

public class CustomAdapter extends ListAdapter<Doacao, CustomAdapter.CustomViewHolder> {

    public CustomAdapter(@NonNull DiffUtil.ItemCallback<Doacao> diff) {
        super(diff);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_doador_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.getTextViewBeneficiario().setText(getItem(position).getBeneficiario());
        holder.getTextViewData().setText(getItem(position).getDataDoacao());
        holder.getTextViewTipo().setText(getItem(position).getTipoDoacao());
        holder.getTextViewQtd().setText(getItem(position).getQtdDoacao());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewBeneficiario;
        private TextView textViewData;
        private TextView textViewTipo;
        private TextView textViewQtd;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            textViewBeneficiario = itemView.findViewById(R.id.main_doador_list_benef);
            textViewData = itemView.findViewById(R.id.main_doador_list_data);
            textViewTipo = itemView.findViewById(R.id.main_doador_list_tipo);
            textViewQtd = itemView.findViewById(R.id.main_doador_list_qtd);
        }

        public TextView getTextViewData() {
            return textViewData;
        }

        public TextView getTextViewTipo() {
            return textViewTipo;
        }

        public TextView getTextViewQtd() {
            return textViewQtd;
        }

        public TextView getTextViewBeneficiario() {
            return textViewBeneficiario;
        }
    }

    static class DoacaoDiff extends DiffUtil.ItemCallback<Doacao> {

        @Override
        public boolean areItemsTheSame(@NonNull Doacao oldItem, @NonNull Doacao newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Doacao oldItem, @NonNull Doacao newItem) {
            return (oldItem.getId() == newItem.getId());
        }
    }
}
