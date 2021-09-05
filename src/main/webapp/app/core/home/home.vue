<template>
    <div class="home row">
        <div class="col-md-3">
		    
            <span class="santeplantes img-plantemedoc rounded"></span>
			<span class="santeplantes img-arthemisiaplante      rounded"></span>
			<span class="santeplantes img-oseille      rounded"></span>
        </div>
        <div class="col-md-9">
            <h1 class="display-4" v-text="$t('home.title')">Welcome, Java Hipster!</h1>
          
			<marquee direction="left" behavior="alternate" style="height:30px;background-color:powderblue;font-family: cursive">GOD Bless You !</marquee>

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username" v-text="$t('home.logged.message', { 'username': username})">You are logged in as user "{{username}}"</span>
                </div>

                <div class="alert alert-warning" v-if="!authenticated">
                      <p>  La phytothérapie, c’est l’emploi de plantes ou de médicaments à base de plantes (poudres, préparations en ampoules, infusions…) pour soigner naturellement les différents maux du corps humain.

La phytothérapie est très certainement la meilleure approche pour prévenir mais aussi pour soigner la majorité de nos maux du quotidien.</p>
                </div>
				
				<div class="alert alert-success" v-if="!authenticated">
				<b>L’infusion</b> est le processus d’extraction de composés chimiques ou d’arômes à partir de matières végétales dans un solvant tel que l’eau, l’huile ou l’alcool, en permettant à la matière de rester en suspension dans le solvant avec le temps (un processus souvent appelé trempage). Une infusion est aussi le nom du liquide qui en résulte.

				 </div>
				
				<p style="text-align:center">
                  <span class="santeplantes img-moringa rounded"></span>
				   <span class="santeplantes img-datte rounded"></span>
				</p>
				
             
            </div>

           

         
        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
