export class DiaSemanaEnum {
    public static readonly DOMINGO: DiaSemanaEnum = new DiaSemanaEnum(1, 'Domingo', 'DOM');
    public static readonly SEGUNDA: DiaSemanaEnum = new DiaSemanaEnum(2, 'Domingo', 'SEG');
    public static readonly TERCA: DiaSemanaEnum = new DiaSemanaEnum(3, 'Domingo', 'TER');
    public static readonly QUARTA: DiaSemanaEnum = new DiaSemanaEnum(4, 'Domingo', 'QUA');
    public static readonly QUINTA: DiaSemanaEnum = new DiaSemanaEnum(5, 'Domingo', 'QUI');
    public static readonly SEXTA: DiaSemanaEnum = new DiaSemanaEnum(6, 'Domingo', 'SEX');
    public static readonly SABADO: DiaSemanaEnum = new DiaSemanaEnum(7, 'Domingo', 'SAB');
    public static readonly TODOS: DiaSemanaEnum[] = [
        this.DOMINGO, this.SEGUNDA, this.TERCA, this.QUARTA, this.QUINTA, this.SEXTA, this.SABADO
    ];

    private constructor(private id: number, private nome: string, private abreviatura: string) {}

    public getId(): number {
        return this.id;
    }

    public getNome(): string {
        return this.nome;
    }

    public getAbreviatura(): string {
        return this.abreviatura;
    }
}