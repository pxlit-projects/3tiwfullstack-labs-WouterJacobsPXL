import { Injectable } from '@angular/core';
import {Pokemon} from "../models/pokemon.model";

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  pokemons: Pokemon[] = [
    { id: 1, name: "Bulbasaur", type: "grass", icon: "https://img.pokemondb.net/artwork/avif/bulbasaur.avif" },
    { id: 2, name: "Ivysaur", type: "grass", icon: "https://img.pokemondb.net/artwork/avif/ivysaur.avif" },
    { id: 3, name: "Venusaur", type: "grass", icon: "https://img.pokemondb.net/artwork/avif/venusaur.avif" },
    { id: 4, name: "Charmander", type: "fire", icon: "https://img.pokemondb.net/artwork/avif/charmander.avif" },
    { id: 5, name: "Charmeleon", type: "fire", icon: "https://img.pokemondb.net/artwork/avif/charmeleon.avif" },
    { id: 6, name: "Charizard", type: "fire", icon: "https://img.pokemondb.net/artwork/avif/charizard.avif" },
    { id: 7, name: "Squirtle", type: "water", icon: "https://img.pokemondb.net/artwork/avif/squirtle.avif" },
    { id: 8, name: "Wartortle", type: "water", icon: "https://img.pokemondb.net/artwork/avif/wartortle.avif" },
    { id: 9, name: "Blastoise", type: "water", icon: "https://img.pokemondb.net/artwork/avif/blastoise.avif" }
  ];
  getPokemons(): Pokemon[]{
    return this.pokemons
}
}
