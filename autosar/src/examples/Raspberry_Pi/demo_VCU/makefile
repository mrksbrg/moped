
# Project name, used for output files et.c.
PROJECTNAME = demo_VCU

# Default settings
include $(ROOTDIR)/scripts/project_defaults.mk

#include ../config/Rte/Config/Rte.mk
#VPATH += ../config/Rte/Config
#inc-y += ../config/Rte/Config
#inc-y += ../config/Rte/Contract
#
#VPATH += ../config/$(BOARDDIR)
#inc-y += ../config/$(BOARDDIR)

include ../config/$(board_name)/Rte/Config/Rte.mk
VPATH += ../config/$(board_name)/Rte/Config
inc-y += ../config/$(board_name)/Rte/Config
inc-y += ../config/$(board_name)/Rte/Contract

VPATH += ../config/$(board_name)
inc-y += ../config/$(board_name)

VPATH += $(ROOTDIR)/boards/Raspberry_Pi/config
inc-y += $(ROOTDIR)/boards/Raspberry_Pi/config
obj-y += $(ROOTDIR)/boards/Raspberry_Pi/config/simple_stdio.o
CFLAGS += -DSIMPLE_STDIO

# Project files (adds all .c files in project root)
PROJECT_C_FILES=$(notdir $(wildcard ../*.c))
obj-y += $(PROJECT_C_FILES:%.c=%.o)

build-bin-y = VCU-kernel.img
inc-y += ..


ifeq ($(SQUAWK), true)
# SQUAWK paths
J2ME_HOME = $(ROOTDIR)/../../../squawk
OBJ_BUILDDIR = $(J2ME_HOME)/vmcore/build
CFLAGS += -DSQUAWK -DBUILD_VCU

LDFLAGS += -lm
lib-y += -lm

# SQUAWK fp-object files
obj-y += $(OBJ_BUILDDIR)/s_scalbn.o
obj-y += $(OBJ_BUILDDIR)/k_sin.o
obj-y += $(OBJ_BUILDDIR)/w_sqrt.o
obj-y += $(OBJ_BUILDDIR)/e_rem_pio2.o
obj-y += $(OBJ_BUILDDIR)/s_sin.o
obj-y += $(OBJ_BUILDDIR)/s_fabs.o
obj-y += $(OBJ_BUILDDIR)/s_tan.o
obj-y += $(OBJ_BUILDDIR)/s_copysign.o
obj-y += $(OBJ_BUILDDIR)/k_cos.o
obj-y += $(OBJ_BUILDDIR)/s_ceil.o
obj-y += $(OBJ_BUILDDIR)/e_sqrt.o
obj-y += $(OBJ_BUILDDIR)/s_floor.o
obj-y += $(OBJ_BUILDDIR)/fp_bytecodes.o
obj-y += $(OBJ_BUILDDIR)/s_cos.o
obj-y += $(OBJ_BUILDDIR)/k_tan.o
obj-y += $(OBJ_BUILDDIR)/k_rem_pio2.o
obj-y += $(OBJ_BUILDDIR)/sha.o
obj-y += $(OBJ_BUILDDIR)/squawk.o

## SQUAWK includes
inc-y += $(J2ME_HOME)/vmcore/src/vm
inc-y += $(J2ME_HOME)/vmcore/src/rts/arm-none-eabi-gcc

endif
