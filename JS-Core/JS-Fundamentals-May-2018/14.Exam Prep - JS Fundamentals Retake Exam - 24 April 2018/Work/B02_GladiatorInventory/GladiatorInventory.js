function gladiatorInventory(input) {
    let inventory = [];
    for (let i = 0; i < input.length - 1; i++) {
        if (i === 0){
            inventory = addNewInventory(input[0]);
        }

        let [command, equipment] = input[i].split(' ');
        switch (command){
            case 'Buy':
                buy(inventory, equipment);
                break;
            case 'Trash':
                trash(inventory, equipment);
                break;
            case 'Repair':
                repair(inventory, equipment);
                break;
            case 'Upgrade':
                upgrade(inventory, equipment);
                break;
        }
    }

    console.log(inventory.join(' '));

    function addNewInventory(line) {
        return line.split(' ');
    }

    function buy(inventory, equipment) {
        if (!inventory.includes(equipment)) {
            inventory.push(equipment);
        }
    }

    function trash(inventory, equipment) {
        let index = inventory.indexOf(equipment);
        if (index > -1) {
            inventory.splice(index, 1);
        }
    }

    function repair(inventory, equipment) {
        if (inventory.includes(equipment)) {
            trash(inventory, equipment);
            buy(inventory, equipment);
        }
    }

    function upgrade(inventory, item) {
        let [equipment, upgrade] = item.split('-');
        if (inventory.includes(equipment)) {
            let indexEquipment = inventory.indexOf(equipment);
            let indexInsert = indexEquipment + 1;
            inventory.splice(indexInsert, 0, `${equipment}:${upgrade}`);
        }
    }
}

gladiatorInventory([
    'SWORD Shield Spear',
    'Buy Bag',
    'Trash Shield',
    'Repair Spear',
    'Upgrade SWORD-Steel',
    'Fight!'
]);