package com.example.aula05oficina;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/calcular")
public class CalculadorServlet extends HttpServlet {
    private String idadePorExtenso(int dia, int mes, int ano) {
        LocalDate localdate = LocalDate.now();

        int diaAtual = localdate.getDayOfMonth();
        int mesAtual = localdate.getMonthValue();
        int anoAtual = localdate.getYear();

        int anos = anoAtual - ano;
        int meses = mesAtual - mes;
        int dias = diaAtual - dia;

        if (meses < 0) {
            anos = anos - 1;
            meses = meses + 12;
        }
        if (dias < 0) {
            meses = meses - 1;
            dias = dias + 30;
        }
        //<valor de anos > anos, meses e <valor de dias > dias;
        String string = anos + " anos, " + meses + " meses e " + dias + " dias";
        return string;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");

        try {
            int dia = Integer.parseInt(request.getParameter("dia"));
            int mes = Integer.parseInt(request.getParameter("mes"));
            int ano = Integer.parseInt(request.getParameter("ano"));

            out.println("<html><body>");
            out.println("<h1>Olá, " + nome + "!<br/>" + "Você tem " + idadePorExtenso(dia,mes,ano));
            out.println("</body></html>");
        } catch (ArithmeticException exception) {
            out.println("<html><body><h1>Erro na conversão de algum dos valores.</h1></body></html>");
        }
        out.close();
    }
}
