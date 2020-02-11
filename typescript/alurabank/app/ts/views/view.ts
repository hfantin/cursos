// uma das formas do compilador aceitar a variavel $ do jquery:
// declare var $: any;

abstract class View<T> {

    private _elemento: JQuery;
    
    constructor(seletor: string) {
        this._elemento = $(seletor);
    }

    update(model: T) {
        this._elemento.html(this.template(model));
    }

    abstract template(model: T): string;
}