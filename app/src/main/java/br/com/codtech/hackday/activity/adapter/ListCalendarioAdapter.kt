package br.com.codtech.hackday.activity.adapter

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import br.com.codtech.hackday.R
import br.com.codtech.hackday.activity.AdicionarCalendarioActivity
import br.com.codtech.hackday.activity.MainActivity

import br.com.codtech.hackday.activity.dao.BancoController
import br.com.codtech.hackday.activity.model.Calendario



class ListCalendarioAdapter(private val activity: MainActivity) : BaseAdapter() {
    val TAG = "ListCalendarioAdapter"

    var list: List<Calendario>? = null
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(activity)
        dataUpdate()
    }

    fun dataUpdate(){
        // Pega o progress bar

        var swipeRefresh = activity.getSwipeRefresh()
        swipeRefresh.isRefreshing = true
            //buscar no BD AQUI
         val crud: BancoController = BancoController(activity.baseContext)


        list = crud.carregaDados();
        if(list == null){
            activity.alerta("Calendarios Vazios!!", Toast.LENGTH_SHORT)
        }else{

            // Ajusta alterações
            this@ListCalendarioAdapter.notifyDataSetChanged()
            // Seta invisible no progress bar
            swipeRefresh.isRefreshing = false
        }
        swipeRefresh.isRefreshing = false


    }

    override fun getCount(): Int {
        if(list == null){
            return 0
        }
        return list!!.size
    }

    override fun getItem(i: Int): Calendario? {
        if(list == null){
            return null
        }
        return list!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.calendario_row, parent, false)
        } else {
            view = convertView
        }

        // Get Categoria
        val calendario = getItem(i)

        // Label
        var textDataInicio = view?.findViewById<TextView>(R.id.text_data_inicio)
        textDataInicio?.text = calendario!!.dataInicio

        // Label
        var textDataFim= view?.findViewById<TextView>(R.id.text_data_fim)
        textDataFim?.text = calendario!!.dataFim

        // Edit button
        var editButton = view?.findViewById<ImageButton>(R.id.editButton)
        editButton?.setOnClickListener {
            var intent = Intent(activity, AdicionarCalendarioActivity::class.java)
            var bundle: Bundle = Bundle()
            bundle.putSerializable("instance", calendario)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }


        var removeButton = view?.findViewById<ImageButton>(R.id.removeButton)
        removeButton?.setOnClickListener {

            // Usando o Snackbar para confirmar a remoção
            Snackbar.make(view!!, "Deseja remover esta calendario?", Snackbar.LENGTH_LONG).setAction("Sim", {



                val crud: BancoController = BancoController(activity.baseContext)
                crud.deletaRegistro(calendario!!.id);
                activity.alerta("Calendario removida com sucesso!", Toast.LENGTH_SHORT)
                dataUpdate()
            }).show()


        }

        // Ação de clique
        view?.setOnClickListener({
          // this.activity.alerta("Clicou em ${categoria?.nome}")
        })

        return view
    }
}