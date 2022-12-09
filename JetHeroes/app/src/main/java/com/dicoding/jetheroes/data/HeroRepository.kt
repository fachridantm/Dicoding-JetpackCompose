package com.dicoding.jetheroes.data

import com.dicoding.jetheroes.model.Hero
import com.dicoding.jetheroes.model.HeroesData.heroes

class HeroRepository {
    fun getHeroes(): List<Hero> = heroes

    fun searchHeroes(query: String): List<Hero> = heroes.filter {
        it.name.contains(query, ignoreCase = true)
    }
}