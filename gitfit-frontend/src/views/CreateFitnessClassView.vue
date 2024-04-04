<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import ErrorModal from '@/components/ErrorModal.vue';
import SuccessModal from '@/components/SuccessModal.vue';
import FitnessClassCreationForm from '@/components/FitnessClassCreationForm.vue';
import { useFitnessClassStore } from '@/stores/fitnessClassCreationStore';

const router = useRouter();

const fitnessClassStore = useFitnessClassStore();
const showModal = ref(false);
const showSuccessModal = ref(false);
const errorMessage = ref('');
const message = ref('Fitness class proposed. Owner will review it.');

const handleFitnessClassCreation = async (fitnessClassData) => {
  try {
    const response = await fitnessClassStore.createFitnessClass(fitnessClassData);
    if (response) {
      showSuccessModal.value = true;
    }
  } catch (error) {
    errorMessage.value = error.message;
    showModal.value = true;
  }
};

const closeSuccessModalAndRedirect = () => {
  showSuccessModal.value = false;
  router.push('/'); // Redirect to home page
};

</script>

<template>
  <div>
    <FitnessClassCreationForm @create-fitnessClass="handleFitnessClassCreation" />
    <ErrorModal :show="showModal" :message="errorMessage" @update:show="showModal = $event" />
    <SuccessModal :show="showSuccessModal" :message="message" @update:show="closeSuccessModalAndRedirect" />
  </div>
</template>