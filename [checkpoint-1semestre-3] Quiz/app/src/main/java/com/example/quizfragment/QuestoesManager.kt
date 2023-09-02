package com.example.quizfragment

import kotlin.random.Random

class QuestoesManager {

    val questoes = listOf<Questao>(
        Questao(
            "Qual o menor e o maior país do mundo?",
            mutableListOf(
                Opcao("Vaticano e Rússia", true),
                Opcao("Nauru e China", false),
                Opcao("Mônaco e Canadá", false),
                Opcao("Malta e Estados Unidos", false)
            )
        ),
        Questao(
            "Qual o livro mais vendido no mundo a seguir à Bíblia?",
            mutableListOf(
                Opcao("O Senhor dos Anéis", false),
                Opcao("Dom Quixote", true),
                Opcao("O Pequeno Príncipe", false),
                Opcao("Um Conto de Duas Cidades", false)
            )
        ),
        Questao(
            "Atualmente, quantos elementos químicos a tabela periódica possui?",
            mutableListOf(
                Opcao("113", false),
                Opcao("109", false),
                Opcao("108", false),
                Opcao("118", true)
            )
        )
    )

    fun getQuestion() = questoes[Random.nextInt(questoes.size)]

}