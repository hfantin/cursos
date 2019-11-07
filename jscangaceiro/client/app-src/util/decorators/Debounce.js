export function debounce(milissegundos = 500) {

    return function(target, key, descriptor) {
        console.log(`debounce com decorator - key=${key}`);
        const metodoOriginal = descriptor.value;
        let timer = 0;
        descriptor.value = function (...args) {
            console.log(`debounce.value - args=${args}`);
            if(event) event.preventDefault();
            clearTimeout(timer);
            // chama metodo original depois de x milissegundos
            timer = setTimeout(() => metodoOriginal.apply(this, args), milissegundos);
        }
        return descriptor;
    }
}