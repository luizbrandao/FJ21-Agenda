package br.com.luizbrandao.mvc.logica;

import br.com.luizbrandao.dao.ContatoDAO;
import br.com.luizbrandao.modelo.Contato;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraContatoLogic implements Logica {
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Contato contato = new Contato();
        long id = Long.parseLong(request.getParameter("id"));
        contato.setId(id);
        contato.setNome(request.getParameter("nome"));
        contato.setEndereco(request.getParameter("endereco"));
        contato.setEmail(request.getParameter("email"));

        String dataEmTexto = request.getParameter("dataNascimento");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(date);

        contato.setDataNascimento(dataNascimento);

        ContatoDAO dao = new ContatoDAO();
        dao.alterar(contato);

        RequestDispatcher rd = request.getRequestDispatcher("/lista-contatos-elegante.jsp");
        rd.forward(request, response);
        System.out.println("Alterando contato ... "+contato.getNome());

    }
}