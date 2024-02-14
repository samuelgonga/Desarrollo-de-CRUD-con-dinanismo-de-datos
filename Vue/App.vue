<script scope>
import axios from 'axios';

export default {
  data() {
    return {
      personajes: null,
      pelicula: {
        "name": '',
        "anio": null,
        "genero": null,
        "image": null,
        "description": ''
      },
      categories: [{ text: 'Selecciona Una', value: null }, 'Drama', 'Terror', 'Romance', 'Suspenso'],      
    }
  },
  mounted() {
    this.created()
  },
  methods: {
    created() {
      axios
        .get("http://localhost:8080/api/peliculas")  //http://localhost:5173/api/peliculas
        .then((response) => {
          this.personajes = response.data;
          console.log(response);
        })
        .catch((error) => {
          console.log("There was an error: " + error);
        });
    },
    async onSubmit(event) {
      await axios.post('http://localhost:8080/api/peliculas', this.pelicula)
        .then((response) => {
          //this.created()
          console.log(response)
        }, (error) => {
          console.log(error);
        });
    },
    onReset(event) {
      event.preventDefault()
      this.pelicula.description = ''
      this.pelicula.name = ''
      this.pelicula.genero = ''
      this.pelicula.anio = ''
      this.pelicula.image = ''
    },
    img(e){
      const file = event.target.files[0];
      const reader = new FileReader();
      
      reader.onload = () => {
        this.imageData = reader.result;
      };
      
      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }
}
</script>

<template>
  <div id="app">
    <header>
      <div class="text-center">
        <h1>Peliculas</h1>
        <div>
          <b-button v-b-modal.modal-1 class="btn btn-success">Agregar</b-button>

          <b-modal id="modal-1" title="Agregar Pelicula" hide-footer="true">

            <b-form @submit="onSubmit" @reset="onReset" v-if="true">

              <b-form-group id="input-group-name" label="Nombre:" label-for="input-name">
                <b-form-input id="input-name" v-model="pelicula.name" type="text" required></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-description" label="Descripción:" label-for="input-description">
                <b-form-input id="input-description" v-model="pelicula.description" required></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-category" label="Genero:" label-for="input-category">
                <b-form-select id="input-category" v-model="pelicula.genero" :options="categories"
                  placeholder="Seleccione" class="btn col-md-12 border" required></b-form-select>
              </b-form-group>

              <b-form-group id="input-group-anio" label="Año:" label-for="input-anio">
                <b-form-input id="input-anio" v-model.number="pelicula.anio" type="number" required></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-image" label="Imagen:" label-for="input-image">
                <input accept="image/png,image/jpeg" type="file" class="form-control" id="input-image" @change="img" required >
              </b-form-group>

              <div class="col-5 mx-auto container mt-3">
                <b-button type="submit" variant="success">Aceptar</b-button>
                <b-button type="reset" variant="danger" style="margin-left: 0.5rem;">Borrar</b-button>
              </div>

              <p>
                {{ pelicula }}
              </p>
            </b-form>

          </b-modal>
        </div>

      </div>
    </header>
    <div class="container row mx-auto mt-4">
      <div class="col-md-4" v-for="personaje in personajes" :key="personaje.id">
        <b-card :title="personaje.nombre" :img-src="personaje.imagen" img-alt="Image" img-top tag="article"
          style="max-width: 20rem; max-height: 30rem;" class="mb-2 mt-2">
          <b-card-text>
            Descripcion: {{ personaje.descripcion }},
            Año: {{ personaje.anio }}
          </b-card-text>
        </b-card>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
