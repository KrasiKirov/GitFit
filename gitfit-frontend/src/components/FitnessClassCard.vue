<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    fitnessClass: {
        type: Object,
        required: true
    }
});
</script>

<template>
    <div class="flex flex-col bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl m-3">
        <div class="md:flex items-stretch">
            <!-- rest of your template -->

            <div class="md:flex-shrink-0">
                <!-- You can replace this div with an image if you have one for each class -->
                <div class="h-48 md:h-auto md:w-48 bg-blue-500"></div>
            </div>
            <div class="p-8">
                <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">{{ fitnessClass.name
                    }}
                </div>
                <p class="block mt-1 text-lg leading-tight font-medium text-black">{{ fitnessClass.description
                    }}</p>
                <p class="mt-2 text-gray-500">Status: {{ fitnessClass.status }}</p>
            </div>

        </div>
    </div>
</template>



<style scoped></style>