<script setup>
import { defineProps} from 'vue'
import { updateFitnessClassStatus } from '@/api'

const props = defineProps({
    fitnessClass: {
        type: Object,
        required: true
    }
})

const emit = defineEmits(['update'])

const approveClass = async () => {
    await updateFitnessClassStatus(props.fitnessClass.name, 'APPROVED')
    emit('update')
}

const rejectClass = async () => {
    await updateFitnessClassStatus(props.fitnessClass.name, 'REJECTED')
    emit('update')
}
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
                <div class="mt-4">
                    <font-awesome-icon icon="heart" class="text-red-500 mr-4 cursor-pointer text-2xl"
                        @click="approveClass" />
                    <font-awesome-icon icon="thumbs-down" class="text-gray-500 cursor-pointer text-2xl"
                        @click="rejectClass" />
                </div>
            </div>

        </div>
    </div>
</template>



<style scoped></style>